package lab1.Options;

import java.nio.file.Path;

public interface IOptionsProvider {
    ServerOptions getOptions(Path path);
}
