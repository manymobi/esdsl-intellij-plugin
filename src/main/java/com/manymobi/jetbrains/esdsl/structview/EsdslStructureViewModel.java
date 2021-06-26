package com.manymobi.jetbrains.esdsl.structview;

import com.intellij.ide.structureView.StructureViewModel;
import com.intellij.ide.structureView.StructureViewModelBase;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.util.treeView.smartTree.Sorter;
import com.manymobi.jetbrains.esdsl.psi.EsdslPSIFileRoot;
import org.jetbrains.annotations.NotNull;

public class EsdslStructureViewModel extends StructureViewModelBase implements StructureViewModel.ElementInfoProvider {
    public EsdslStructureViewModel(EsdslPSIFileRoot root) {
        super(root, new EsdslStructureViewRootElement(root));
    }

    @NotNull
    public Sorter[] getSorters() {
        return new Sorter[]{Sorter.ALPHA_SORTER};
    }

    @Override
    public boolean isAlwaysLeaf(StructureViewTreeElement element) {
        return !isAlwaysShowsPlus(element);
    }

    @Override
    public boolean isAlwaysShowsPlus(StructureViewTreeElement element) {
        Object value = element.getValue();
        return value instanceof EsdslPSIFileRoot;
    }
}
