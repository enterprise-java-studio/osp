package com.javastudio.lms.tutorial.web.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;

public class DebugListener implements PhaseListener {

    private static final long serialVersionUID = 1069270717751614672L;

    @Inject
    private Logger logger;

    @Override
    public void beforePhase(PhaseEvent event) {
        logger.info("--- beforePhase {}", event.getPhaseId());
    }

    @Override
    public void afterPhase(PhaseEvent event) {
        logger.info("--- afterPhase {}", event.getPhaseId());
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }
}
