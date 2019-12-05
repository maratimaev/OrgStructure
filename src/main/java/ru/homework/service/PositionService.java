package ru.homework.service;

import ru.homework.model.Position;

import java.util.Optional;

public interface PositionService {
    Position findById(int id);
}
