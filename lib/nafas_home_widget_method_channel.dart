import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'nafas_home_widget_platform_interface.dart';

/// An implementation of [NafasHomeWidgetPlatform] that uses method channels.
class MethodChannelNafasHomeWidget extends NafasHomeWidgetPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('nafas_home_widget');

  @override
  Future<String?> getPlatformVersion() async {
    final version = await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }
}
