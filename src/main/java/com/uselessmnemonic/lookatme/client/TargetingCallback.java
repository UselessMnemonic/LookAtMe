package com.uselessmnemonic.lookatme.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.Box;

public class TargetingCallback implements ClientTickEvents.StartTick {

    private static TargetingCallback instance;
    public static ClientTickEvents.StartTick instance() {
        if (instance == null) {
            instance = new TargetingCallback();
        }
        return instance;
    }

    private TargetingCallback() {}

    @Override
    public void onStartTick(MinecraftClient client) {
        var player = client.player;
        if (player == null) return;

        // Get seeable entities from 10 blocks around
        var world = player.getEntityWorld();
        var box = Box.of(player.getPos(), 10, 10, 10);
        var targets = world.getEntitiesByClass(LivingEntity.class, box, player::canSee);

        // spawn particle around all targets
        targets.forEach(t -> {
            var x = t.getX();
            var y = t.getY() + t.getHeight() + 0.2;
            var z = t.getZ();
            world.addParticle(ParticleTypes.SMOKE, x, y, z, 0, 0.1, 0);
        });
    }
}
