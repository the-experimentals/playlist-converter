interface IRoute{
    path: string
    name: string
    exact: boolean
    component: any
    props?: any
    children?: IRoute[]
}

export default IRoute