package com.manymobi.jetbrains.esdsl.structview;

import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiFile;
import com.manymobi.jetbrains.esdsl.Icons;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class EsdslRootPresentation implements ItemPresentation {
	protected final PsiFile element;

	protected EsdslRootPresentation(PsiFile element) {
		this.element = element;
	}

	@Nullable
	@Override
	public Icon getIcon(boolean unused) {
		return Icons.SAMPLE_ICON;
	}

	@Nullable
	@Override
	public String getPresentableText() {
		return element.getVirtualFile().getNameWithoutExtension();
	}

	@Nullable
	@Override
	public String getLocationString() {
		return null;
	}
}
