/*
 * This file is part of LegacyProjectiles, licensed under the MIT License.
 *
 *  Copyright (c) JadedMC
 *  Copyright (c) contributors
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */
package net.jadedmc.legacyprojectiles.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class ProjectileLaunchListener implements Listener {

    @EventHandler
    public void onLaunch(@NotNull final ProjectileLaunchEvent event) {
        final Projectile projectile = event.getEntity();

        // Ignore if the shooter isn't an entity.
        if(!(projectile.getShooter() instanceof Entity shooter)) {
            return;
        }

        // Genuinely no idea why I have to do this. When standing still the player has a Y velocity of -0.0784000015258789.
        // My best guess is gravity, but default gravity is supposedly 0.8 ¯\_(ツ)_/¯
        final Vector shooterVelocity = shooter.getVelocity().add(new Vector(0,0.0784000015258789,0));

        // Set the corrected velocity.
        final Vector finalVelocity = projectile.getVelocity().subtract(shooterVelocity);
        projectile.setVelocity(finalVelocity);
    }
}