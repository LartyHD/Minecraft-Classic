/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.lartyhd.spigot.classic.shopwarps.inventory

import de.lartyhd.spigot.classic.shopwarps.Injector
import de.lartyhd.spigot.classic.shopwarps.builder.InventoryBuilder
import de.lartyhd.spigot.classic.shopwarps.builder.ItemBuilder
import de.lartyhd.spigot.classic.shopwarps.warp.Warp
import org.bukkit.Material
import org.bukkit.entity.HumanEntity
import org.bukkit.inventory.InventoryView
import org.bukkit.inventory.ItemStack
import java.util.*

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
        inventory = InventoryBuilder(54, "§9Shops")
                .fillWith(glass, 0, 8)
                .fillWith(glass, 45, 53)
                .setItem(4, ItemBuilder(Material.SIGN)
                        .setName("§9Infos")
                        .setLore(listOf(
                                " ",
                                "§7Von §bLars Artmann §8| §bLartyHD",
                                "§7für §bClassic §7programmiert",
                                "",
                                "§7Danke an §bTerra §7und §bAyumu §7für das coole §bProjekt"
                        ))
                        .build())
                .setItem(46, ItemBuilder(Material.INK_SACK, 1.toShort())
                        .setName("§cLösche deinen Warp")
                        .build())
                .setItem(52, ItemBuilder(Material.INK_SACK, 10.toShort())
                        .setName("§aSetze deinen eigenen Warp")
                        .addLore("§7Setzt den Warp auf deine aktuelle Location")
                        .build())
    }


    fun removeAndUpdateWarps(uuid: UUID) {
        for (warp in ArrayList(warps)) if (warp.uuid == uuid) {
            warps.remove(warp)
            updateWarps()
        }
    }

    private fun remove(uuid: UUID) {
        for (warp in ArrayList(warps)) if (warp.uuid == uuid) warps.remove(warp)
    }


    fun add(warp: Warp) {
        remove(warp.uuid)
        warps.add(warp)
        updateWarps()
    }

    fun updateWarps() {
        updateInventory()
        setWarps()
    }


    private fun updateInventory() {
        inventory.replaceWith(ItemStack(Material.AIR), 9, 44)
        for (i in 9 until warps.size + 9) inventory.setItem(i, ItemBuilder(warps[i - 9].getItem())
                .addLore(" ", "§eDieser Shop gehört: §a${warps[i - 9].uuid}")
                .build())
    }

    private fun setWarps() = Injector.configManager.setWarps(warps)

    fun getWarp(name: String): Warp? {
        for (warp in warps) if (warp.name == name) return warp
        return null
    }

    fun getWarp(uuid: UUID): Warp? {
        for (warp in warps) if (warp.uuid == uuid) return warp
        return null
    }

    fun openInventory(humanEntity: HumanEntity): InventoryView = humanEntity.openInventory(inventory.build())
}