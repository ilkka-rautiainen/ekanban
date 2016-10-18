package fi.aalto.ekanban.builders;

import java.util.List;

import org.bson.types.ObjectId;

import fi.aalto.ekanban.models.db.games.Board;
import fi.aalto.ekanban.models.db.games.Card;
import fi.aalto.ekanban.models.db.games.EventCard;
import fi.aalto.ekanban.models.db.phases.Phase;

public final class BoardBuilder {
    private String id;
    private List<Card> backlogDeck;
    private List<EventCard> eventCardDeck;
    private List<Phase> phases;

    private BoardBuilder() {
        this.id = ObjectId.get().toString();
    }

    public static BoardBuilder aBoard() {
        return new BoardBuilder();
    }

    public BoardBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public BoardBuilder withBacklogDeck(List<Card> backlogDeck) {
        this.backlogDeck = backlogDeck;
        return this;
    }

    public BoardBuilder withEventCardDeck(List<EventCard> eventCardDeck) {
        this.eventCardDeck = eventCardDeck;
        return this;
    }

    public BoardBuilder withPhases(List<Phase> phases) {
        this.phases = phases;
        return this;
    }

    public Board build() {
        Board board = new Board();
        board.setId(id);
        board.setBacklogDeck(backlogDeck);
        board.setEventCardDeck(eventCardDeck);
        board.setPhases(phases);
        return board;
    }
}
