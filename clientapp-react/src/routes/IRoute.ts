interface IRoute{
    path: string
    name: string
    exact: boolean
    component: any
    props?: any
    childern?: IRoute[]
}

export default IRoute