package com.epam.thead.logic;

import com.epam.thead.entity.Lot;

import java.util.List;

public interface JsonLot {
    List<Lot> create(String filePath);
}
