package com.github.GhostNemesis12.nemesisworld;

import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.LevelChunk;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.level.ChunkEvent;
import net.neoforged.neoforge.event.level.ChunkEvent.Load;
import net.neoforged.neoforge.event.level.ChunkEvent.Unload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChunkHandler {
    private static final ToroidalConverter converter = new ToroidalConverter(-30000000, 30000000, -30000000, 30000000);
    private static final Logger LOGGER = LoggerFactory.getLogger(ChunkHandler.class);

    // Método para manejar eventos de carga de chunks
    @SubscribeEvent
    public static void onChunkLoad(Load event) {
        LevelChunk chunk = (LevelChunk) event.getChunk();
        if (chunk != null) {
            int toroidalX = converter.toToroidalX(chunk.getPos().x);
            int toroidalZ = converter.toToroidalZ(chunk.getPos().z);

            // Si las coordenadas toroidales son diferentes, ajustamos el chunk
            if (toroidalX != chunk.getPos().x || toroidalZ != chunk.getPos().z) {
                System.out.println("Ajustando chunk a coordenadas toroidales: " + toroidalX + ", " + toroidalZ);
                // Aquí puedes realizar ajustes o redirigir la generación de chunks si es necesario
            }
        }
    }

    // Método para manejar eventos de descarga de chunks
    @SubscribeEvent
    public static void onChunkUnload(Unload event) {
        // Aquí puedes manejar la descarga de chunks si es necesario
        LevelChunk chunk = (LevelChunk) event.getChunk();
        if (chunk != null) {
            System.out.println("El chunk ha sido descargado: " + chunk.getPos());
        }
    }
}
