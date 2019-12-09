package ru.homework.dto.profile;

public interface OutputProfile {

    /**
     *  Вывод короткого списка полей
     */
    interface Short {}

    /**
     * Вывод дополнительных полей при запросе информации о департаменте
     */
    interface DepartmentInfo extends Short {}
}
