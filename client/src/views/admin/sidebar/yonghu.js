

export default [

        {
        label:'个人中心',
        to:'',
        children:[
                        {
                label:'修改资料',
                to:'/admin/yonghuupdtself',
            },
                        {
                label:'修改密码',
                to:'/admin/mod',
            },
                        {
                label:'我的收藏',
                to:'/admin/shoucangjilu',
            },
                        {
                label:'我的留言',
                to:'/admin/liuyanban_liuyanren',
            },
                    ]
    },
    {
        label:'订单管理',
        to:'',
        children:[
            {
                label:'查询订单',
                to:'/admin/yuding_yudingren',
            },
        ]
    },
    ]
