import Main from '@/components/main';
// t_peony路由
export const tableDesgin = [
    {
        path: '/table-design',
        name: 'TableDesign',
        component: Main,
        meta: {
            title: '数据库',
            icon: 'icon iconfont icon-layers'
        },
        children: [
            //  牡丹花 列表 1
            {
                path: '/table-design/table-index',
                name: 'TableIndex',
                meta: {
                    title: '数据表设计',
                    icon: 'icon iconfont icon-layers',
                    privilege: [
                        { title: '查询', name: 'table-index-query' },
                        { title: '新增', name: 'table-index-add' },
                        { title: '编辑', name: 'table-index-update' },
                        { title: '删除', name: 'table-index-delete' },
                    ]
                },
                component: () => import('@/views/business/table-design/table-index.vue')
            }
        ]
    }
];
