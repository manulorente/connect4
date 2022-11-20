package mcloudapps.connectFour.controllers;

import mcloudapps.connectFour.types.Color;
import mcloudapps.connectFour.views.BoardView;
import mcloudapps.connectFour.views.ViewFactory;
import mcloudapps.utils.models.Coordinate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public abstract class ControllerTest {
    
    @Mock
    protected BoardView boardView;

    @Mock
    protected ViewFactory viewFactory;

    protected Controller controller;

    @Test
    public void testGivenControllerWhenWriteBoardThenCorrectColorsCaptured() {
        when(this.viewFactory.createBoardView()).thenReturn(this.boardView);
        this.controller = this.getController(
                "       ",
                "       ",
                "       ",
                "       ",
                "       ",
                "RY     ");
        this.controller.writeBoard();
        ArgumentCaptor<Color> argumentCaptor = ArgumentCaptor.forClass(Color.class);
        ArgumentCaptor<Coordinate> argumentCaptorCoordinate = ArgumentCaptor.forClass(Coordinate.class);
        verify(this.boardView, atLeastOnce()).set(argumentCaptorCoordinate.capture(), argumentCaptor.capture());
        String board =
                "RY     " +
                "       " +
                "       " +
                "       " +
                "       " +
                "       ";
        assertThat(argumentCaptor.getAllValues(), is(this.stringToColors(board)));
        verify(this.boardView).write();
    }

    protected abstract Controller getController(String... rows);

    protected List<Color> stringToColors(String board) {
        List<Color> colors = new ArrayList<>();
        for (char character : board.toCharArray()) {
            colors.add(this.charToColor(character));
        }
        return colors;
    }

    private Color charToColor(char character) {
        switch (character) {
            case 'R':
                return Color.R;
            case 'Y':
                return Color.Y;
            default:
                return null;
        }
    }

}
