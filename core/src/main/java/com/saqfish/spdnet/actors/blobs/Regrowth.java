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

package com.saqfish.spdnet.actors.blobs;

import com.saqfish.spdnet.Dungeon;
import com.saqfish.spdnet.actors.Actor;
import com.saqfish.spdnet.actors.Char;
import com.saqfish.spdnet.actors.buffs.Buff;
import com.saqfish.spdnet.actors.buffs.Roots;
import com.saqfish.spdnet.effects.BlobEmitter;
import com.saqfish.spdnet.effects.particles.LeafParticle;
import com.saqfish.spdnet.levels.Level;
import com.saqfish.spdnet.levels.Terrain;
import com.saqfish.spdnet.scenes.GameScene;

public class Regrowth extends Blob {
	
	@Override
	protected void evolve() {
		super.evolve();
		
		if (volume > 0) {
			int cell;
			for (int i = area.left; i < area.right; i++) {
				for (int j = area.top; j < area.bottom; j++) {
					cell = i + j*Dungeon.level.width();
					if (off[cell] > 0) {
						int c = Dungeon.level.map[cell];
						int c1 = c;
						if (c == Terrain.EMPTY || c == Terrain.EMBERS || c == Terrain.EMPTY_DECO) {
							c1 = (cur[cell] > 9 && Actor.findChar( cell ) == null)
									? Terrain.HIGH_GRASS : Terrain.GRASS;
						} else if ((c == Terrain.GRASS || c == Terrain.FURROWED_GRASS)
								&& cur[cell] > 9 && Dungeon.level.plants.get(cell) == null && Actor.findChar( cell ) == null ) {
							c1 = Terrain.HIGH_GRASS;
						}

						if (c1 != c) {
							Level.set( cell, c1 );
							GameScene.updateMap( cell );
						}

						Char ch = Actor.findChar( cell );
						if (ch != null
								&& !ch.isImmune(this.getClass())
								&& off[cell] > 1) {
							Buff.prolong( ch, Roots.class, TICK );
						}
					}
				}
			}
			Dungeon.observe();
		}
	}
	
	@Override
	public void use( BlobEmitter emitter ) {
		super.use( emitter );
		
		emitter.start( LeafParticle.LEVEL_SPECIFIC, 0.2f, 0 );
	}
}
