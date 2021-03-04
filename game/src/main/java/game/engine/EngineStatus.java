package game.engine;

public enum EngineStatus {
    NOT_STARTED,
    PAUSED_FOR_ANIMATIONS,
    RESUMING_AFTER_ANIMATIONS,
    AWAITING_FOR_INPUT,
    PROCESSING_INPUT,
    STOPPED
}
