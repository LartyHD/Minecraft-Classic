/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.lartyhd.spigot.classic.shopwarps

import org.bukkit.plugin.java.JavaPlugin

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 01.05.2018 07:56.
 * Last edit 01.05.2018
 */
@Suppress("unused") //Plugin entry point
class ShopWarps : JavaPlugin() {

    override fun onEnable() {
        println(this)
        println(Injector)
        Injector.init(this)
    }

//    override fun onDisable() {
//        configManager.setWarps(WarpsInventory.warps)
//    }
}
