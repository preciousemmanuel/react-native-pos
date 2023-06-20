import { NativeModules, Platform } from 'react-native';

const LINKING_ERROR =
  `The package 'react-native-nexgo-android-pos' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';

const NexgoAndroidPos = NativeModules.NexgoAndroidPos
  ? NativeModules.NexgoAndroidPos
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );

export function multiply(a: number, b: number): Promise<number> {
  return NexgoAndroidPos.multiply(a, b);
}


type item={
  price:number,
  name:string,
  quantity:number
}






export function printReciept(
  logo:string,companyName:string,area:string,
  country:string,email:string,
  phone:string,orderId:string,
  status:string="SUCCESSFUL",
  createdAt:string,
  items:Array<item>,
  currency:string,
  totalAmount:number,
  deliveryAmount:number,
  deliveryType:string  
  ): Promise<void> {
  return NexgoAndroidPos.printReciept(logo,companyName,area,
    country,email,
    phone,orderId,
    status,
    createdAt,
    items,
    currency,
    totalAmount,
    deliveryAmount,
    deliveryType
    );
}

export function detectCard():Promise<string>{
  

return  NexgoAndroidPos.cardReader();
  
}

export function inputCard():Promise<string>{
  

  return  NexgoAndroidPos.inputPinTest();
    
  }
