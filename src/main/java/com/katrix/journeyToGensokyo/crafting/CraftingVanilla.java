/**
 * This class was created by <Katrix>. It's distributed as
 * part of the Journey To Gensokyo Mod. Get the Source Code in github:
 * https://github.com/Katrix-/JTG
 * 
 * Journey To Gensokyo is Open Source and distributed under the
 * a modifed Botania license: https://github.com/Katrix-/JTG/blob/master/LICENSE.md
 */

package com.katrix.journeyToGensokyo.crafting;

import com.katrix.journeyToGensokyo.block.JTGBlock;
import com.katrix.journeyToGensokyo.handler.ConfigHandler;
import com.katrix.journeyToGensokyo.item.JTGItem;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class CraftingVanilla {
	
	public static void init() {
		
    	if(ConfigHandler.OresEnabled) {
    		GameRegistry.addSmelting(new ItemStack(JTGBlock.gensokyoOreBlock, 1, 0), new ItemStack(JTGItem.gensokyoIngotItem, 1, 0), 0.1f);
            GameRegistry.addSmelting(new ItemStack(JTGBlock.gensokyoOreBlock, 1, 1), new ItemStack(JTGItem.gensokyoIngotItem, 1, 1), 0.1f);
            GameRegistry.addSmelting(new ItemStack(JTGBlock.gensokyoOreBlock, 1, 2), new ItemStack(JTGItem.gensokyoIngotItem, 1, 2), 0.1f);
            
    		GameRegistry.addSmelting(new ItemStack(JTGItem.gensokyoDustItem, 1, 0), new ItemStack(JTGItem.gensokyoIngotItem, 1, 0), 0.1f);
            GameRegistry.addSmelting(new ItemStack(JTGItem.gensokyoDustItem, 1, 1), new ItemStack(JTGItem.gensokyoIngotItem, 1, 1), 0.1f);
            GameRegistry.addSmelting(new ItemStack(JTGItem.gensokyoDustItem, 1, 2), new ItemStack(JTGItem.gensokyoIngotItem, 1, 2), 0.1f);
    	}

    	if(ConfigHandler.NotesEnabled) {
            GameRegistry.addRecipe(new ItemStack(JTGBlock.keyStoneBlock), " r ", "sss", "nnn",
                    's', Items.string, 'r', Blocks.stone, 'n', new ItemStack(JTGItem.oldGensokyoSpellItem, 1, 2));
    	}
    	else{
            GameRegistry.addRecipe(new ItemStack(JTGBlock.keyStoneBlock), " r ", "sss", "nnn",
                    's', Items.string, 'r', Blocks.stone, 'n', Items.paper);
    	}
    	
    	if(ConfigHandler.NotesEnabled) {
            GameRegistry.addRecipe(new ItemStack(JTGItem.gensokyoNotesItem, 1, 1), " n ", "nrn", " n ",
            		'r', new ItemStack(JTGItem.gensokyoNotesItem, 1, 0), 'n', new ItemStack(JTGItem.oldGensokyoSpellItem, 1, 0));
    	}

	}

}