import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'nafas_home_widget_method_channel.dart';

abstract class NafasHomeWidgetPlatform extends PlatformInterface {
  /// Constructs a NafasHomeWidgetPlatform.
  NafasHomeWidgetPlatform() : super(token: _token);

  static final Object _token = Object();

  static NafasHomeWidgetPlatform _instance = MethodChannelNafasHomeWidget();

  /// The default instance of [NafasHomeWidgetPlatform] to use.
  ///
  /// Defaults to [MethodChannelNafasHomeWidget].
  static NafasHomeWidgetPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [NafasHomeWidgetPlatform] when
  /// they register themselves.
  static set instance(NafasHomeWidgetPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }
}
