import 'package:flutter_test/flutter_test.dart';
import 'package:nafas_home_widget/nafas_home_widget.dart';
import 'package:nafas_home_widget/nafas_home_widget_platform_interface.dart';
import 'package:nafas_home_widget/nafas_home_widget_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockNafasHomeWidgetPlatform
    with MockPlatformInterfaceMixin
    implements NafasHomeWidgetPlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');
}

void main() {
  final NafasHomeWidgetPlatform initialPlatform = NafasHomeWidgetPlatform.instance;

  test('$MethodChannelNafasHomeWidget is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelNafasHomeWidget>());
  });

  test('getPlatformVersion', () async {
    NafasHomeWidget nafasHomeWidgetPlugin = NafasHomeWidget();
    MockNafasHomeWidgetPlatform fakePlatform = MockNafasHomeWidgetPlatform();
    NafasHomeWidgetPlatform.instance = fakePlatform;

    expect(await nafasHomeWidgetPlugin.getPlatformVersion(), '42');
  });
}
