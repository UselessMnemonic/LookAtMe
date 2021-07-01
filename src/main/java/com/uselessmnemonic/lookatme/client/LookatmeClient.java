package com.uselessmnemonic.lookatme.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

@net.fabricmc.api.Environment(net.fabricmc.api.EnvType.CLIENT)
public class LookatmeClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientTickEvents.START_CLIENT_TICK.register(TargetingCallback.instance());
    }
}
