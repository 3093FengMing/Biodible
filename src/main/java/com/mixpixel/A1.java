package com.mixpixel;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class A1 implements Listener {
    /*
    @EventHandler
    public void playerJoin(PlayerJoinEvent event){
      Player q = event.getPlayer();
      String n = q.getName();
      event.joinMessage(Component.text(n+"����ѧ��ѧ�±�ѧ��"));
    */

    //}
    @EventHandler
    public void click(InventoryClickEvent event){
        Player a = (Player) event.getWhoClicked();
        boolean placeholderClick = Biodible.lists.placeHolderList.contains(event.getRawSlot());
        if(event.getView().title().equals(Component.text("Biodible Menu", TextColor.color(13,71,91)))){
            switch(event.getRawSlot()){
                case 3:
                    String l = String.valueOf(a.getLevel());
                    String l1 =  String.valueOf(a.getTicksLived()/200);
                    a.sendMessage("ѧ�±�ѧ��ѧ�ˣ�"+l+"���ˡ�");
                    a.sendMessage("��ԭ�����ˣ�"+l1+"���ˡ�");
                    //p.getInventory().addItem(itemX)
                    //System.out.println("Biodible for Server: CD.1 was clicked.");
                    event.setCancelled(true);
                break;
                case 5:
                    if(a.isOp()) {
                        ItemStack item = new ItemStack(Material.BOOK, 1);
                        ItemMeta meta = item.getItemMeta();
                        meta.displayName(Component.text("��f�����飨1�ǣ�"));
                        List<Component> lore0 = new ArrayList<>();
                        lore0.add(Component.text("��fһ��������"));
                        lore0.add(Component.text("��f������������±���ʦ"));
                        meta.lore(lore0);
                        item.setItemMeta(meta);
                        a.getInventory().addItem(item);
                        //System.out.println("Biodible for Server: CD.2 was clicked.");
                        event.setCancelled(true);
                    }
                    else {
                        a.sendMessage("����ԭ�񣬲����á�");
                        event.setCancelled(true);
                    }
                break;

                case 22:
                    Inventory inventory = event.getInventory();
                    ItemStack orgItem = inventory.getItem(19);
                    if (orgItem != null) {
                        ItemMeta meta = orgItem.getItemMeta();
                        Component name = meta.displayName();
                        String nameE = meta.getDisplayName();
                        //System.out.println("Debug:�������" + name + nameE);
                        //System.out.println("Debug:���ڵ��б�Ϊ" + Biodible.lists.nameElist);
                        int e = Biodible.lists.nameElist.indexOf(nameE);
                        //System.out.println(e);
                        if (e == -1) {
                        a.sendMessage(Component.text("����Ʒ����ǿ����"));
                        event.setCancelled(true);
                        } else if (Objects.equals(nameE, Biodible.lists.nameElist.get(e))) {
                            int itemAmount = orgItem.getAmount();
                            if (itemAmount !=1){
                                a.sendMessage(Component.text("һ��ֻ��ǿ��1����Ʒ��"));
                                a.getInventory().addItem(orgItem);
                                event.setCancelled(true);
                                inventory.clear(19);
                                return;
                            }
                            int winPossibility = Biodible.lists.winProbablityList.get(e);
                            Random random = new Random();
                            int thisWinRandInt = random.nextInt(100);
                            if (Biodible.lists.useShardList.get(e)) {
                                ItemStack shard = inventory.getItem(31);
                                if (shard == null){
                                    a.sendMessage(Component.text("ǿ������Ʒ��Ҫ�����"));
                                }
                                else {
                                    ItemMeta metaS = shard.getItemMeta();
                                    String providedShardName = metaS.getDisplayName();
                                    if (providedShardName.equals(Biodible.lists.useShardNameList.get(e))){
                                        int shardAmount = shard.getAmount();
                                        if (shardAmount >= Biodible.lists.useShardAmountList.get(e)){
                                            ItemStack takeStack = shard.clone();
                                            takeStack.setAmount(Biodible.lists.useShardAmountList.get(e));
                                            inventory.removeItem(takeStack);
                                        }
                                        else{
                                            a.sendMessage(Component.text("ǿ�������������㡣"));
                                        }
                                    }
                                }
                            }
                            if (thisWinRandInt < winPossibility) {
                                meta.displayName(Biodible.lists.winItemNameList.get(e));
                                List<String> lore0 = Biodible.lists.winItemLoreList.get(e);
                                meta.setLore(lore0);
                                orgItem.setItemMeta(meta);
                                a.sendMessage("��ϲ�㣬ǿ���ɹ���");
                                inventory.clear(19);
                                inventory.setItem(25, orgItem);
                                event.setCancelled(true);
                            } else {
                                a.sendMessage("���ź���ǿ��ʧ�ܡ�");
                                inventory.clear(19);
                                event.setCancelled(true);
                            }
            }}

            else {
                a.sendMessage("����Ҫ�ȷ�������ǵ���Ʒ��");
                event.setCancelled(true);
            }
            break;
        }
        if(event.getView().title().equals(Component.text("Biodible Menu", TextColor.color(13,71,91)))&&placeholderClick){
            event.setCancelled(true);
        }
}}
    @EventHandler
    public void close(InventoryCloseEvent event){
        if(event.getView().title().equals(Component.text("Biodible Menu", TextColor.color(13,71,91)))){
            Inventory inventory = event.getInventory();
            ItemStack itemIn19 = inventory.getItem(19);
            if(itemIn19!=null) {
                Player player = (Player) event.getPlayer();
                player.getInventory().addItem(itemIn19);
            }
            ItemStack itemIn25 = inventory.getItem(25);
            if(itemIn25!=null) {
                Player player = (Player) event.getPlayer();
                player.getInventory().addItem(itemIn25);
            }
            ItemStack itemIn31 = inventory.getItem(31);
            if(itemIn31!=null) {
                Player player = (Player) event.getPlayer();
                player.getInventory().addItem(itemIn31);
            }
        }
}
}
