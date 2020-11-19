package com.mini.core.util.tree;

import javax.annotation.Nonnull;
import java.util.List;

public abstract class AbstractTree<T extends ITree> implements ITree<T> {
	
	@Override
	public final void addChild(@Nonnull T child) {
		ITree.super.addChild(child);
	}
	
	@Override
	public void setChildren(List<T> children) {
		this.children = children;
	}
	
	@Override
	public List<T> getChildren() {
		return children;
	}
	
	private List<T> children;
}
