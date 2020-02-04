package com.mini.code.impl;

import com.mini.code.Configure;
import com.mini.code.Configure.BeanItem;
import com.mini.code.Configure.ClassInfo;
import com.mini.code.util.MethodSpecBuilder;
import com.mini.core.jdbc.JdbcInterface;
import com.mini.core.jdbc.builder.SQLBuilder;
import com.mini.core.jdbc.mapper.BeanMapper;
import com.mini.core.jdbc.model.Paging;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.File;
import java.util.List;

import static com.mini.core.util.StringUtil.firstLowerCase;
import static com.mini.core.util.StringUtil.toJavaName;
import static javax.lang.model.element.Modifier.DEFAULT;
import static javax.lang.model.element.Modifier.PUBLIC;

public final class CodeDaoBase {
	/**
	 * 生成代码
	 * @param configure 数据库与实体配置信息
	 * @param info      所有类的信息
	 * @param bean      数据库实体信息
	 * @param cover     true-文件存在时覆盖，false-文件存在时不覆盖
	 */
	public static void generator(Configure configure, ClassInfo info, BeanItem bean, boolean cover) throws Exception {
		if (!cover && configure.exists(info.getBaseDaoPackage(), info.getBaseDaoName())) {
			return;
		}
		
		JavaFile.builder(info.getBaseDaoPackage(), TypeSpec
			// 接口名称
			.interfaceBuilder(info.getBaseDaoName())
			// public 接口
			.addModifiers(PUBLIC)
			// 继承 BasicsDao 接口
			.addSuperinterface(JdbcInterface.class)
			// 生成类注释文档
			.addJavadoc("$L.java \n", info.getBaseDaoName())
			.addJavadoc("@author xchao \n")
			// 生成 deleteById 方法
			.addMethod(MethodSpecBuilder
				// 方法名称
				.methodBuilder("deleteById")
				// default 方法
				.addModifiers(DEFAULT, PUBLIC)
				// 返回类型
				.returns(int.class)
				// 方法文档注释
				.addJavadoc("删除实体信息 \n")
				// 为每个主键字体生成一个参数和参数文档注释
				.forAdd(info.getPKFieldList(), (method, fieldInfo) -> {
					String name = toJavaName(fieldInfo.getFieldName(), false);
					method.addParameter(fieldInfo.getTypeClass(), name);
					method.addJavadoc("@param $N $N \n", name, fieldInfo.getRemarks());
				})
				// 返回结果文档注释
				.addJavadoc("@return 执行结果 \n")
				.addCode("return execute(new $T() {{ \n ", SQLBuilder.class)
				.addStatement("delete().from($T.TABLE)", info.getBeanClass())
				// 为每个主键字段添加下令条件限制查询方法体
				.forAdd(info.getPKFieldList(), (method, fieldInfo) -> {
					String db_name = fieldInfo.getColumnName().toUpperCase();
					String name = toJavaName(fieldInfo.getFieldName(), true);
					method.addCode("\t// $L \n", fieldInfo.getRemarks());
					method.addStatement("\twhere($S, $T.$L)", "%s = ?", info.getBeanClass(), db_name);
					method.addStatement("\tparams($N)", firstLowerCase(name));
				})
				// 方法体
				.addStatement("}})")
				// 构建
				.build())
			
			// 生成 queryById 方法
			.addMethod(MethodSpecBuilder
				// 方法名称
				.methodBuilder("queryById") //
				// default 方法
				.addModifiers(DEFAULT, PUBLIC) //
				// 返回类型
				.returns(info.getBeanClass()) //
				// 方法文档注释
				.addJavadoc("根据ID查询实体信息 \n")
				// 添加实体主键参数和参数注释
				.forAdd(info.getPKFieldList(), (method, fieldInfo) -> {
					String name = toJavaName(fieldInfo.getFieldName(), false);
					method.addParameter(fieldInfo.getTypeClass(), name);
					method.addJavadoc("@param $N $N \n", name, fieldInfo.getRemarks());
				})
				// 添加返回信息注释
				.addJavadoc("@return 实体信息 \n")
				// 方法体
				.addCode("return queryObject(new $T($T.class) {{ \n", SQLBuilder.class, info.getBeanClass())
				// 为每个主键字段添加条件限制查询方法体
				.forAdd(info.getPKFieldList(), (method, fieldInfo) -> {
					String db_name = fieldInfo.getColumnName().toUpperCase();
					String name = toJavaName(fieldInfo.getFieldName(), true);
					method.addCode("\t// $L \n", fieldInfo.getRemarks());
					method.addStatement("\twhere($S, $T.$L)", "%s = ?", info.getBeanClass(), db_name);
					method.addStatement("\tparams($N)", firstLowerCase(name));
				})
				// 方法体
				.addStatement("}}, $T.create($T.class))", BeanMapper.class, info.getBeanClass())
				// 构建
				.build())
			
			// 生成 queryAll 方法
			.addMethod(MethodSpecBuilder
				// 方法名称
				.methodBuilder("queryAll")
				// default 方法
				.addModifiers(DEFAULT, PUBLIC)
				// 返回List<实体> 类型
				.returns(ParameterizedTypeName.get(ClassName.get(List.class), info.getBeanClass())) //
				// 方法文档注释
				.addJavadoc("查询所有实体信息 \n") //
				// 方法返回类型注释
				.addJavadoc("@return 实体信息列表 \n") //
				// 方法体
				.addCode("return queryList(new $T($T.class) {{ \n", SQLBuilder.class, info.getBeanClass()) //
				// 方法体
				.addCode("// \t   \n") //
				// 方法体
				.addStatement("}},  $T.create($T.class))", BeanMapper.class, info.getBeanClass())
				// 构建
				.build())
			
			// 生成 queryAll(Paging) 方法
			.addMethod(MethodSpecBuilder
				// 方法名称
				.methodBuilder("queryAll")
				// default 方法
				.addModifiers(DEFAULT, PUBLIC)
				// 返回List<实体> 类型
				.returns(ParameterizedTypeName.get(ClassName.get(Paging.class), info.getBeanClass()))
				// 添加分页参数-页码数
				.addParameter(int.class, "page")
				// 添加分页参数-每页条数
				.addParameter(int.class, "limit")
				// 添加方法注释
				.addJavadoc("查询所有实体信息 \n")
				// 添加 Paging 参数注释
				.addJavadoc("@param $N 分页-页码数\n", "page")
				.addJavadoc("@param $N 分页- 每页条数\n", "limit")
				// 方法返回类型注释
				.addJavadoc("@return 实体信息列表 \n")
				// 方法体
				.addCode("return queryPaging(page, limit, new $T($T.class)  {{ \n", SQLBuilder.class, info.getBeanClass())
				// 方法体
				.addCode("// \n") //
				// 方法体
				.addStatement("}},  $T.create($T.class))", BeanMapper.class, info.getBeanClass())
				// 构建
				.build())
			
			// 生成类
			.build())
			// 生成文件信息，并将文件信息转出到本地
			.build().writeTo(new File(configure.getClassPath()));
		
		System.out.println("====================================");
		System.out.println("Code Dao Base : " + info.getBeanName() + "\r\n");
	}
}
