package fi.aalto.ekanban.services.turnplayers;

import static fi.aalto.ekanban.ApplicationConstants.DAY_THRESHOLD_TO_RETURN_DICE_CAST_ACTIONS;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fi.aalto.ekanban.services.ai.assignresources.AssignResourcesAIService;
import fi.aalto.ekanban.services.ai.assignresources.DiceCastService;
import fi.aalto.ekanban.services.ai.drawfrombacklog.DrawFromBacklogAIService;
import fi.aalto.ekanban.services.ai.movecards.MoveCardsAIService;
import fi.aalto.ekanban.services.ActionExecutorService;
import fi.aalto.ekanban.models.db.games.Game;
import fi.aalto.ekanban.models.*;

@Service
public class NormalTurnPlayerService implements TurnPlayer {

    @Autowired
    private ActionExecutorService actionExecutorService;

    @Autowired
    private AssignResourcesAIService assignResourcesAIService;

    @Autowired
    private MoveCardsAIService moveCardsAIService;

    @Autowired
    private DrawFromBacklogAIService drawFromBacklogAIService;

    @Autowired
    private DiceCastService diceCastService;

    public Game playTurn(Game game, Turn turn) {
        game.setLastTurn(turn);
        game = actionExecutorService.adjustWipLimits(game, turn.getAdjustWipLimitsAction());
        game = assignResourcesWithAI(game);
        game = moveCardsWithAI(moveCardsAIService, actionExecutorService, game);
        game = drawFromBacklogWithAI(drawFromBacklogAIService, actionExecutorService, game);
        return game;
    }

    private Game assignResourcesWithAI(Game game) {
        List<DiceCastAction> diceCastActions = diceCastService.getDiceCastActions(game);
        List<AssignResourcesAction> assignResourcesActions = assignResourcesAIService.getAssignResourcesActions(game,
                diceCastActions);
        game = actionExecutorService.assignResources(game, assignResourcesActions);
        game.getLastTurn().setAssignResourcesActions(assignResourcesActions);
        if (game.getCurrentDay() >= DAY_THRESHOLD_TO_RETURN_DICE_CAST_ACTIONS) {
            game.getLastTurn().setDiceCastActions(diceCastActions);
        }
        return game;
    }

}
