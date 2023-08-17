package com.mixpixel;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.util.HSVLike;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class MyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        //if (args.length == x)
        if (args.length == 0){
            sender.sendMessage("�򿪲˵� /ydb cd��Client Only��");
            sender.sendMessage("���Բ�� /ydb debug");
            sender.sendMessage("���ز�� /ydb reload��OP Only��");
            return true;
        }
        if (args[0].equals("debug")) {
            sender.sendMessage("Biodible for MixPixel Client Ed. is running normally.");
            System.out.println("Biodible for MixPixel is running normally.");
            return true;
        }
        if (args[0].equals("cd")) {
            Inventory inv;
            inv = Bukkit. createInventory((InventoryHolder) sender, 45,Component.text("Biodible Menu", TextColor.color(13,71,91)));
            //null, InventoryType.xxx
            ItemStack i = new ItemStack(Material.BLUE_ICE,1);
            ItemMeta meta = i.getItemMeta();
            meta.displayName(Component.text("Start Process",TextColor.color(HSVLike.fromRGB(0,149,163)), TextDecoration.BOLD));
            List<Component> lore = new ArrayList<>();
            lore.add(Component.text("��f�������ѧѧϰ����ô���أ���"));
            lore.add(Component.text("��f��±���ʦΪ���ṩ�˲��ԣ�"));
            lore.add(Component.text("��f���ھͿ�ʼ�ɣ�"));
            meta.lore(lore);
            i.setItemMeta(meta);
            ItemStack i0 = new ItemStack(Material.BOOK, 1);
            ItemMeta meta0 = i0.getItemMeta();
            meta0.displayName(Component.text("��fGet Biology Book"));
            List<Component> lore0 = new ArrayList<>();
            lore0.add(Component.text("��f��Ϊ����ѧѧϰ����׼�����𣿣�"));
            lore0.add(Component.text("��f��±���ʦΪ���ṩ�������飡"));
            lore0.add(Component.text("��f���ھ���ȡ�ɣ�������OP��ȡ������ԭ������ˣ�"));
            meta0.lore(lore0);
            i0.setItemMeta(meta0);
            ItemStack placeholder = new ItemStack(Material.BLACK_STAINED_GLASS_PANE,1);
            ItemMeta metaPlaceholder = placeholder.getItemMeta();
            metaPlaceholder.displayName(Component.text("��fBiodible Menu"));
            placeholder.setItemMeta(metaPlaceholder);
            ItemStack arrow = new ItemStack(Material.ARROW,1);
            ItemMeta meta1 = arrow.getItemMeta();
            meta1.displayName(Component.text("��f����"));
            List<Component> lore1 = new ArrayList<>();
            lore1.add(Component.text("����������������ԭ����"));
            meta1.lore(lore1);
            arrow.setItemMeta(meta1);
            inv.setItem(3,i);
            inv.setItem(5,i0);
            inv.setItem(22,arrow);
            Iterator<Integer> iterator = Biodible.lists.placeHolderList.iterator();
            while (iterator.hasNext()){
                inv.setItem(iterator.next(),placeholder);
            }
            Player p = (Player) sender;
            p.openInventory(inv);
            return true;
        }
        if(args[0].equals("reload")){
            if(sender.isOp()){
                Biodible.main.loadConfigurations();
                sender.sendMessage(Component.text("��fBiodible Has Reloaded."));
            }
            else{
                sender.sendMessage(Component.text("��fBiodible Warning: You don't have the permission for this!"));

            }
            return true;
        }
        else {
            sender.sendMessage("This is invalid for Biodible for MixPixel.");
        }
        return false;
    }
}
