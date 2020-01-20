const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const { CleanWebpackPlugin } = require('clean-webpack-plugin');
const CopyPlugin = require('copy-webpack-plugin');


module.exports = {
    entry:{
        app: './src/index.js',
    },
    output: {
        filename: 'main.js',
        path: path.resolve(__dirname,'dist')
    },
    plugins: [
        new CleanWebpackPlugin(),
        new HtmlWebpackPlugin({
            title: "Homeless map"
        }),
        new CopyPlugin([
            {from: 'data', to: 'data'},
            {from: 'images', to: 'images'}
        ])
    ],
    module: {
        rules: [
            {
                test: /\.css$/,
                use: [
                    'style-loader',
                    'css-loader'
                ],
            },{
                test: /\.(png|svg|jpg|gif)$/,
                use: ['file-loader']
            }
        ]
    },
    devServer: {
        contentBase: path.resolve(__dirname, 'dist'),
        port: 9000
    },
    node: {
        fs: 'empty'
    }
};
