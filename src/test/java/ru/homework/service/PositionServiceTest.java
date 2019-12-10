package ru.homework.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.homework.dto.PositionView;
import ru.homework.mapper.MapperFacade;
import ru.homework.model.Position;
import org.apache.commons.lang3.RandomStringUtils;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PositionServiceTest {

    @Autowired
    private PositionService positionService;

    private Position testPosition;

    private char[] rus = new char[]{'А','Б','В','Г','Д','Е','Ж','З','И','К','Л',
                                    'а','б','в','г','д','е','ж','з','и','к','л'};

    @BeforeEach
    public void createTestEnvironment() {
        createTestPosition();
    }

    private void createTestPosition() {
        PositionView testPositionView = new PositionView(
                RandomStringUtils.random(10, 0, 0, true, true, rus),
                RandomStringUtils.random(100, 0, 0, true, true, rus)
        );
        testPosition = positionService.create(testPositionView);
    }

    @Test
    void checkSuccessGetPositionById() {
        Position result = positionService.findById(testPosition.getId());
        assertThat(testPosition, is(result));
    }

    @AfterEach
    public void deleteTestEnvironment() {
        deleteTestPosition();
    }

    private void deleteTestPosition() {
        positionService.delete(testPosition.getId());
    }
}
