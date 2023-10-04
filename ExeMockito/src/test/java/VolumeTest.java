
import com.vvs.AudioManager;
import com.vvs.VolumeUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VolumeTest {
    @Mock
    AudioManager audioManager;

    @InjectMocks
    VolumeUtil volumeUtil;

    @Test
    public void testAudioManagerSetVolume() {
        // Arrange
        doNothing().when(audioManager).setVolume(100);

        // Act
        volumeUtil.maximizeVolume(100);

        // Verify
        verify(audioManager, times(1)).setVolume(100);
    }
}
