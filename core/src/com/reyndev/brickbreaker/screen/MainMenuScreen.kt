package com.reyndev.brickbreaker.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.reyndev.brickbreaker.Main

class MainMenuScreen(private val game: Main) : Screen {
    private lateinit var stage: Stage
    private lateinit var layout: Table

    private var width: Float = 720f
    private var height: Float = 1600f

    // Font
    private lateinit var fontGenerator: FreeTypeFontGenerator
    private lateinit var fontParameter: FreeTypeFontParameter

    // Game title
    private lateinit var lblTitleStyle: LabelStyle
    private lateinit var lblTitleFont: BitmapFont
    private lateinit var lblTitle: Label

    // Start button
    private lateinit var btnStartStyle: TextButtonStyle
    private lateinit var btnStartFont: BitmapFont
    private lateinit var btnStart: TextButton

    // Leaderboard button
    private lateinit var btnLeaderboardStyle: TextButtonStyle
    private lateinit var btnLeaderboardFont: BitmapFont
    private lateinit var btnLeaderboard: TextButton

    init {
        // Stage
        stage = Stage(ExtendViewport(width, height, OrthographicCamera()))

        Gdx.input.inputProcessor = stage

        // UI table
        layout = Table()
        layout.setFillParent(true)
        layout.align(Align.top)
        stage.addActor(layout)

        // FreeTypeFont init
        fontGenerator = FreeTypeFontGenerator(Gdx.files.internal("font/kenney_pixel.ttf"))

        // Game title
        fontParameter = FreeTypeFontParameter()
        fontParameter.size = 112
        lblTitleFont = fontGenerator.generateFont(fontParameter)

        lblTitleStyle = LabelStyle()
        lblTitleStyle.font = lblTitleFont

        lblTitle = Label("Brick Breaker", lblTitleStyle)
        layout.add(lblTitle).height(1000f)

        // Start button init
        fontParameter = FreeTypeFontParameter()
        fontParameter.size = 36
        fontParameter.color = Color(0.1f, 0.1f, 0.1f, 1f)
        btnStartFont = fontGenerator.generateFont(fontParameter)

        btnStartStyle = TextButtonStyle()
        btnStartStyle.up = TextureRegionDrawable(TextureRegion(Texture("gfx/button_up.png")))
        btnStartStyle.down = TextureRegionDrawable(TextureRegion(Texture("gfx/button_down.png")))
        btnStartStyle.font = btnStartFont

        btnStart = TextButton("Start Game", btnStartStyle)
        layout.row()
        layout.add(btnStart).height(75f).pad(100f)

        // Leaderboard button init
        fontParameter = FreeTypeFontParameter()
        fontParameter.size = 36
        fontParameter.color = Color(0.1f, 0.1f, 0.1f, 1f)
        btnLeaderboardFont = fontGenerator.generateFont(fontParameter)

        btnLeaderboardStyle = TextButtonStyle()
        btnLeaderboardStyle.up = TextureRegionDrawable(TextureRegion(Texture("gfx/button_up.png")))
        btnLeaderboardStyle.down = TextureRegionDrawable(TextureRegion(Texture("gfx/button_down.png")))
        btnLeaderboardStyle.font = btnLeaderboardFont

        btnLeaderboard = TextButton("Leaderboard", btnLeaderboardStyle)
        layout.row()
        layout.add(btnLeaderboard).height(75f)
    }

    override fun show() {
    }

    override fun render(delta: Float) {
        // Clear frame buffer
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        ScreenUtils.clear(0f, 0f, 1f, 1f)

        // Draw stage
        stage.act(delta)
        stage.draw()

        // Input
        if (Gdx.input.isTouched) {
            val touchPos = Vector3()
            touchPos.set(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), 0f)
            stage.viewport.camera.unproject(touchPos)
        }

        btnStart.addListener(object : ClickListener() {
            override fun touchDown(
                event: InputEvent?,
                x: Float,
                y: Float,
                pointer: Int,
                button: Int
            ): Boolean {
                Gdx.app.log("MainMenuScreen", "Start button is touched")
                return true
            }
        })
    }

    override fun resize(width: Int, height: Int) {
        stage.viewport.update(width, height, true)
    }

    override fun pause() {}

    override fun resume() {}

    override fun hide() {}

    override fun dispose() {
        stage.dispose()
        fontGenerator.dispose()
    }
}