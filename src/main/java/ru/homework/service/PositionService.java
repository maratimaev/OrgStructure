package ru.homework.service;

import ru.homework.model.Position;

import java.util.Optional;

/**
 * Сервис для работы с должностями
 */
public interface PositionService {
    /** Поиск должности
     * @param id должности
     * @return модель должности
     */
    Position findById(int id);
}
