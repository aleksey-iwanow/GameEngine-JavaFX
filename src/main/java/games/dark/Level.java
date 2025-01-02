package games.dark;

import GDK.engine.Vector2;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Level{
    public Vector2[] mPos;
    public Vector2 kPos;
    public Vector2 nPos;
    public Vector2 pPos;
    public Vector2 lockPos;
}
