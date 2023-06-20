# react-native-nexgo-android-pos

this package enables prinitng, card detection and card swipe on android pos

## Installation

```sh
npm install react-native-nexgo-android-pos
```

## Usage for print

```js
import { printReciept } from 'react-native-nexgo-android-pos';

// ...

const result = await printReciept(
    logo:string,
    companyName:string,
    area:string,
    country:string,
    email:string,
    phone:string,
    orderId:string,
    status:string,
    createdAt:string,
    items:Array<{name:string,price:number,quantity:number}>,
    currency:string,
    totalAmount:number,
    deliveryAmount:number,
    deliveryType:string);


## Usage for card detection


import { detectCard } from 'react-native-nexgo-android-pos';

// ...
try{
const result = await detectCard(
    );
    if(result=="SUCCESS"){
        //card detected
    }else{
        //not detected
    }
}catch(e){
//not detected
}


## Usage for input card

import { inputCard } from 'react-native-nexgo-android-pos';

// ...
try{
const result = await inputCard(
    );
    
}catch(e){
//not detected
}

```

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT

---

Made with [create-react-native-library](https://github.com/callstack/react-native-builder-bob)
