package com.github.GhostNemesis12.nemesisworld;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.border.WorldBorder;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.minecraft.world.level.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WorldBorderHandler {
    private static final Logger LOGGER = LogManager.getLogger();

    // Evento que se llama cada vez que un tick del nivel se actualiza
    @SubscribeEvent
    public static void onEntityTick(EntityTickEvent event) {
        LivingEntity entity = (LivingEntity) event.getEntity(); // Obtener la entidad del evento

        if (entity != null) {
            // Obtener el nivel (world) de la entidad usando getCommandSenderWorld() en lugar de acceder directamente al campo 'level'
            Level level = entity.getCommandSenderWorld();

            // Obtener el WorldBorder de la dimensión actual
            WorldBorder worldBorder = level.getWorldBorder();

            // Desactivar la barrera del mundo, estableciendo un valor suficientemente grande o eliminando sus límites
            if (worldBorder != null) {
                worldBorder.setSize(Double.MAX_VALUE); // Establece el tamaño del borde a un valor muy grande
                worldBorder.setCenter(0, 0); // Centra el borde si es necesario (opcional)
                LOGGER.info("El borde del mundo ha sido removido.");
            }
        }
    }
}


