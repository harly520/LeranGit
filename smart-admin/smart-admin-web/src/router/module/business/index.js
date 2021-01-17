
import Main from '@/components/main';

import { peony } from './peony';
import { emailSetting } from './email';
import { keepAlive } from './keep-alive';
import { notice } from './notice';
import { threeRouter } from './three-router';
import {tableDesgin} from "./table-desgin";

// 业务
export const business = [
  {
    path: '/business',
    name: 'Business',
    component: Main,
    meta: {
      title: '业务功能',
      topMenu:true,
      icon: 'icon iconfont iconyoujianguanli'
    },
    children: [
      ...tableDesgin,
      ...peony,
      ...emailSetting,
      ...keepAlive,
      ...notice,
      ...threeRouter
    ]
  }
];