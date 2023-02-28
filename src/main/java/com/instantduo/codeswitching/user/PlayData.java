package com.instantduo.codeswitching.user;

import com.instantduo.codeswitching.common.type.Game;
import com.instantduo.codeswitching.common.type.Subject;
import com.instantduo.codeswitching.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class PlayData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private Subject subject;

    @Enumerated(value = EnumType.STRING)
    private Game game;

    private Integer count;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public PlayData(Subject subject, Game game, Integer count, User user) {
        this.subject = subject;
        this.game = game;
        this.count = count;
        this.user = user;
    }
}
