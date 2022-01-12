/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2021 Evan Debenham
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.saqfish.spdnet.items.potions.elixirs;

import com.saqfish.spdnet.actors.buffs.ArcaneArmor;
import com.saqfish.spdnet.actors.buffs.Buff;
import com.saqfish.spdnet.actors.hero.Hero;
import com.saqfish.spdnet.items.potions.exotic.PotionOfEarthenArmor;
import com.saqfish.spdnet.items.quest.GooBlob;
import com.saqfish.spdnet.sprites.ItemSpriteSheet;

public class ElixirOfArcaneArmor extends Elixir {
	
	{
		image = ItemSpriteSheet.ELIXIR_ARCANE;
	}
	
	@Override
	public void apply(Hero hero) {
		Buff.affect(hero, ArcaneArmor.class).set(5 + hero.lvl/2, 80);
	}
	
	@Override
	public int value() {
		//prices of ingredients
		return quantity * (50 + 40);
	}
	
	public static class Recipe extends com.saqfish.spdnet.items.Recipe.SimpleRecipe {
		
		{
			inputs =  new Class[]{PotionOfEarthenArmor.class, GooBlob.class};
			inQuantity = new int[]{1, 1};
			
			cost = 8;
			
			output = ElixirOfArcaneArmor.class;
			outQuantity = 1;
		}
		
	}
}
