package com.github.GhostNemesis12.nemesisworld;

import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.minecraft.world.entity.LivingEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntityHandler {
    private static final ToroidalConverter converter = new ToroidalConverter(-30000000, 30000000, -30000000, 30000000);
    private static final Logger LOGGER = LogManager.getLogger();

    // Evento que se llama en cada tick de la entidad
    @SubscribeEvent
    public static void onEntityTick(EntityTickEvent event) {
        LivingEntity entity = (LivingEntity) event.getEntity();
        if (entity != null) {
            adjustEntityPosition(entity);
        }
    }

    // Método que ajusta la posición de la entidad a coordenadas toroidales
    private static void adjustEntityPosition(LivingEntity entity) {
        if (entity != null) {
            double posX = entity.getX();
            double posZ = entity.getZ();

            // Verificar si la entidad está fuera de los límites
            if (posX > 30000000 || posX < -30000000 || posZ > 30000000 || posZ < -30000000) {
                double newX = converter.toToroidalX((int) posX);
                double newZ = converter.toToroidalZ((int) posZ);
                entity.setPos(newX, entity.getY(), newZ);

                LOGGER.info("Moviendo entidad a coordenadas toroidales: " + newX + ", " + newZ);
            }
        }
    }
}
