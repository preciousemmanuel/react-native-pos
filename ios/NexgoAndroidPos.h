
#ifdef RCT_NEW_ARCH_ENABLED
#import "RNNexgoAndroidPosSpec.h"

@interface NexgoAndroidPos : NSObject <NativeNexgoAndroidPosSpec>
#else
#import <React/RCTBridgeModule.h>

@interface NexgoAndroidPos : NSObject <RCTBridgeModule>
#endif

@end
