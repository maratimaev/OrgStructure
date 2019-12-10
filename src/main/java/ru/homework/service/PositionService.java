package ru.homework.service;

import ru.homework.dto.PositionView;
import ru.homework.model.Position;

/**
 * Сервис для работы с должностями
 */
public interface PositionService {
    /** Поиск должности
     * @param id должности
     * @return модель должности
     */
    Position findById(int id);

    /** Создание должности
     * @param positionView DTO должности
     */
    Position create(PositionView positionView);

    /** Удаление дожности
     * @param position модель должности
     */
    void delete(Integer positionId);
}
