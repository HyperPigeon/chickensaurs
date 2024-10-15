package net.hyper_pigeon.chickensaurs;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Constants {

	public static final String MOD_ID = "chickensaurs";
	public static final String MOD_NAME = "Chickensaurs";
	public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

	private static final ResourceLocation HUNT_ID =  ResourceLocation.parse("chickensaurs:chickensaur_hunt");
	public static final TagKey<EntityType<?>> HUNT = TagKey.create(Registries.ENTITY_TYPE, HUNT_ID);

	private static final ResourceLocation INTIMIDATE_ID =  ResourceLocation.parse("chickensaurs:chickensaur_intimidate");
	public static final TagKey<EntityType<?>> INTIMIDATE = TagKey.create(Registries.ENTITY_TYPE, INTIMIDATE_ID);

	private static final ResourceLocation GROUP_HUNT_ID =  ResourceLocation.parse("chickensaurs:chickensaur_group_hunt");
	public static final TagKey<EntityType<?>> GROUP_HUNT = TagKey.create(Registries.ENTITY_TYPE, GROUP_HUNT_ID);

}