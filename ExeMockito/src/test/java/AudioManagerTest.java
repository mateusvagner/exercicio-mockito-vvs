import com.vvs.AudioManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AudioManagerTest {

    AudioManager audioManager;

    @Before
    public void setup() {
        audioManager = new AudioManager();
    }

    @Test
    public void testAudioManagerSetVolume() {
        // Arrange
        int expectedVolume = 100;

        // Act
        audioManager.setVolume(expectedVolume);

        // Assert
        Assert.assertEquals(expectedVolume, audioManager.getVolume());
    }
}
