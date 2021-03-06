package de.warhog.fpvlaptracker.util;

import de.warhog.fpvlaptracker.configuration.ApplicationConfig;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public enum AudioFile {
    LAP("lap.wav");

    private static final Logger LOG = LoggerFactory.getLogger(AudioFile.class);
    private static String audioLanguage = "en-EN";

    public static void setAudioLanguage(String language) {
        audioLanguage = language;
    }

    @Component
    public static class AudioLanguageInjector {

        @Autowired
        private ApplicationConfig applicationConfig;

        @PostConstruct
        public void postConstruct() {
            AudioFile.setAudioLanguage(applicationConfig.getAudioLanguage());
        }

    }

    private final String filename;

    AudioFile(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return "audio/" + filename;
    }

}
