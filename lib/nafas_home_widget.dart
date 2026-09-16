
import 'nafas_home_widget_platform_interface.dart';

class NafasHomeWidget {
  Future<String?> getPlatformVersion() {
    return NafasHomeWidgetPlatform.instance.getPlatformVersion();
  }
}
