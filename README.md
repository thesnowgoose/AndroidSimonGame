# AndroidSimonGame
A game to teach university students about Android

### Pasos para completar este juego:

1. Crear un nuevo proyecto

2. Estructura del proyecto:
    - manifest
    - java/
    - res/
    - gradle

3. Activities
    - Life Cycle
    ![alt text](https://i.stack.imgur.com/kChdb.png)

4. Layout
    - Crear relative layout
    - Colocar el center
    - Colocar botones de colores con background solido
    - Agregar colores a res
    - Crear shape en drawables para boton start
    - Agregar btn start con frame layout (str: start, color: #666666)

5. MainActivity (Game logic)
    - Declare and inflate colorBtns on onCreate()
    - Declare and inflate start btn on onCreate() and set onClickListener(new..)
    - Create List colorBtns
    - Create getRandom() and Enum BtnColors
    - Create SynchronyLights(). Explain AsyncTask. Call it from start.onClick()
    - Explain and implements Listener design pattern. Implement onHighlightButtons() on Activity. Leave empty
    - Implement View.OnClickListener and set onClickListener(this) to colorBtns on onCreate()
    - Create List playerColorBtns. Add pressed colorBtn to playerColorBtns list on onClick()
    - Create TransitionDrawables on drawable/ declare them global on Activity and change colorBtns background on layout. Inflate them on onCreate()
    - Create animateColoredButton()
    - Implement TransitionDrawables on onHighlightButtons() and on onClick() calling animateColoredButton()
    - Create eraseGameData() with variables
    - Add game logic to onClick()

6. Add icon
    ![alt text](http://www.virtual-pet-game.com/images/Games/Simon/Simon.svg)

