package com.redpxnda.forgingfundamentals.item;

import net.minecraft.client.Minecraft;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.*;
import java.util.stream.Collectors;

@ParametersAreNonnullByDefault
public class DummyItem extends Item {
    protected Minecraft minecraft;
    public DummyItem() {
        super(new Properties().tab(se.mickelus.tetra.TetraItemGroup.instance).stacksTo(1));
    }

    private CreativeModeTab tetraTab;
    private Item tetraScroll;

    private CreativeModeTab getTetraTab(){
        if(tetraTab==null){
            CreativeModeTab[] groups = CreativeModeTab.TABS;
            for(int i = 0;i<groups.length;i++){
                if(groups[i].getClass().getCanonicalName() != null && groups[i].getClass().getCanonicalName().contains("tetra")){
                    tetraTab = groups[i];
                    return groups[i];
                }
            }
            for(int i = 0;i<groups.length;i++){
                if(groups[i].getClass().getCanonicalName() != null && groups[i].getClass().getCanonicalName().contains("mickelus")) {
                    tetraTab = groups[i];
                    return groups[i];
                }
            }
            return null;
        }
        else{
            return tetraTab;
        }
    }

    private Item getScrollItem(){
        if(tetraScroll==null)
            tetraScroll = Registry.ITEM.get(new ResourceLocation("tetra:scroll_rolled"));
        return tetraScroll;
    }

    public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) {
        if (group.equals(getTetraTab())) {
            items.add(this.setupSchematic("sword/cutlass_blade", "pirate",new String[]{"sword/cutlass_blade"}, false, 2, 12505964, 14, 13, 5, 8));
            items.add(this.setupSchematic("sword/sickle", "pirate",new String[]{"sword/sickle"}, false, 2, 10469589, 9, 14, 1, 10));
            items.add(this.setupSchematic("sword/basket_guard", "pirate",new String[]{"sword/basket_guard"}, false, 2, 14657876, 0, 6, 3, 2));
        }
    }

    private ItemStack setupSchematic(String key, String details, String[] schematics, boolean isIntricate, int material, int tint, Integer... glyphs) {
        ScrollHelper data = new ScrollHelper(key, Optional.ofNullable(details), isIntricate, material, tint, Arrays.asList(glyphs), (List<ResourceLocation>)Arrays.stream(schematics).map((s) -> {
            return new ResourceLocation("tetra", s);
        }).collect(Collectors.toList()), Collections.emptyList());
        ItemStack itemStack = new ItemStack(getScrollItem());
        data.write(itemStack);
        return itemStack;
    }
}
