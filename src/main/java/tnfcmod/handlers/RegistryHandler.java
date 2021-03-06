package tnfcmod.handlers;

import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import net.dries007.tfc.api.recipes.knapping.KnappingRecipe;
import tnfcmod.objects.items.TNFCItems;
import tnfcmod.recipes.OreDicEntries;
import tnfcmod.recipes.TFCRecipes;
import tnfcmod.tnfcmod;

import static tnfcmod.recipes.IERecipes.*;
import static tnfcmod.recipes.LootTablesTNFC.modifyLootTableLoad;
import static tnfcmod.recipes.TFCRecipes.registerOrePileRecipes;
import static tnfcmod.recipes.VanillaRecipes.registerVanillaRecipes;
import static tnfcmod.recipes.VanillaRecipes.removeVanillaRecipes;
import static tnfcmod.tnfcmod.MODID;


@SuppressWarnings({"unused", "WeakerAccess"})
@Mod.EventBusSubscriber(modid = MODID)
public final class RegistryHandler
{
    /**
     * Listen for the register event for creating custom items
     */
    @SubscribeEvent
    public static void addItems(RegistryEvent.Register<Item> event)
    {
        TNFCItems.register(event.getRegistry());
        OreDicEntries.init();
    }

    /**
     * Listen for the register event for models
     */
    @SubscribeEvent
    public static void registerItems(ModelRegistryEvent event)
    {
        TNFCItems.registerModels(event);

    }
    /**
     * Register Knapping Recipe
     */
    @SubscribeEvent
    public static void onRegisterKnappingRecipeEvent(RegistryEvent.Register<KnappingRecipe> event)
    {
        TFCRecipes.registerKnapping(event);
    }


    /**
     * Register Loot Tables
     */
    @SubscribeEvent
    public static void onLootTableLoad(LootTableLoadEvent event)
    {
           modifyLootTableLoad(event);
    }
    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event)
    {
        tnfcmod.getLog().info("Registering TechnodeFirmaCraft recipes");
        removeVanillaRecipes(event);
        registerVanillaRecipes(event);
        registerMetalPressRecipes();
        registerCrusherRecipes();
        registerGardenClocheRecipes();
        registerArcFurnaceRecipes();
        registerOrePileRecipes();



    }
    @SubscribeEvent
    public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
//        ResourceLocation soundID = new ResourceLocation(MOD_ID, "item.flaskbreak");
//        event.getRegistry().register((new SoundEvent(soundID)).setRegistryName(soundID));
    }

}
