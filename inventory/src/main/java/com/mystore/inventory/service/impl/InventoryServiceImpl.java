package com.mystore.inventory.service.impl;

import com.mystore.inventory.dto.InventoryDTO;
import com.mystore.inventory.model.Inventory;
import com.mystore.inventory.repo.InventoryRepo;
import com.mystore.inventory.service.InventoryService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    private InventoryRepo inventoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<InventoryDTO> getAllItems() {
        List<Inventory>itemList = inventoryRepo.findAll();
        return modelMapper.map(itemList, new TypeToken<List<InventoryDTO>>(){}.getType());
    }

    @Override
    public InventoryDTO saveItem(InventoryDTO inventoryDTO) {
        inventoryRepo.save(modelMapper.map(inventoryDTO, Inventory.class));
        return inventoryDTO;
    }

    @Override
    public InventoryDTO updateItem(InventoryDTO inventoryDTO) {
        inventoryRepo.save(modelMapper.map(inventoryDTO, Inventory.class));
        return inventoryDTO;
    }

    @Override
    public String deleteItem(Integer itemId) {
        inventoryRepo.deleteById(itemId);
        return "Item deleted";
    }

    @Override
    public InventoryDTO getItemById(Integer itemId) {
        Inventory item = inventoryRepo.getItemById(itemId);
        return modelMapper.map(item, InventoryDTO.class);
    }
}
