/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.lartyhd.spigot.classic.shopwarps.inventory

import de.lartyhd.spigot.classic.shopwarps.builder.InventoryBuilder
import de.lartyhd.spigot.classic.shopwarps.builder.ItemBuilder
import de.lartyhd.spigot.classic.shopwarps.warp.Warp
import org.bukkit.Material
import org.bukkit.entity.HumanEntity
import org.bukkit.inventory.InventoryView
import org.bukkit.inventory.ItemStack

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 01.05.2018 10:54.
 * Last edit 01.05.2018
 */
object WarpsInventory {

    private val inventory: InventoryBuilder
    val warps: MutableList<Warp> = ArrayList()

    init {
        val glass = ItemBuilder(Material.STAINED_GLASS_PANE, 7.toShort()).setName("§0").build()
        inventory = InventoryBuilder(54, "§9Shops").fillWith(glass, 0, 8).fillWith(glass, 45, 53).setItem(4, ItemBuilder(Material.SIGN).setName("§9Infos").setLore(listOf(" ", "§7Von §bLars Artmann §8| §bLartyHD", "§7für §bClassic §7programmiert", "", "§7Danke an Terra für das coole §bProjekt")).build()).setItem(49, ItemBuilder(Material.NETHER_STAR).setName("§9Setze deinen eigenen Warp").addLore("§7Setzt den Warp auf deine aktuelle Location").build())
    }

    fun updateWarps() {
        inventory.fillWith(ItemStack(Material.AIR), 9, 44)
        for (i in 9 until warps.size + 9) inventory.setItem(i, warps[i].getItem())
    }

    fun getWarp(name: String): Warp? {
        for (warp in warps) if (warp.name == name) return warp
        return null
    }

    fun openInventory(humanEntity: HumanEntity): InventoryView = humanEntity.openInventory(inventory.build())

}