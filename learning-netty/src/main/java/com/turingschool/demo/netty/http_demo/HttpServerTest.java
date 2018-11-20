package com.turingschool.demo.netty.http_demo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;

public class HttpServerTest {

    public void openServer(int port){
        ServerBootstrap bootstrap=new ServerBootstrap();
        bootstrap.channel(NioServerSocketChannel.class);
        EventLoopGroup boot=new NioEventLoopGroup(1);
        EventLoopGroup work=new NioEventLoopGroup(8);
        bootstrap.group(boot,work);

        bootstrap.childHandler(new ChannelInitializer() {
            protected void initChannel(Channel ch) throws Exception {
                ch.pipeline().addLast("http-decode", new HttpRequestDecoder());//解码request 1
                ch.pipeline().addLast("http-aggregator",new HttpObjectAggregator(6536));
                ch.pipeline().addLast("http-encode", new HttpResponseEncoder());// 编码response 3
                ch.pipeline().addLast("http-server-handler", new HttpServerHandler()); // 业务处理2
            }
        });
        try {
            ChannelFuture f = bootstrap.bind(port).sync();
            System.out.println("服务启动成功："+port);
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boot.shutdownGracefully();
            work.shutdownGracefully();
        }
    }

    private static class HttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
            /*HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(msg.retain());
            decoder.offer(msg.);
            List<InterfaceHttpData> list = decoder.getBodyHttpDatas();*/
            if(msg instanceof LastHttpContent) {
                FullHttpResponse response =
                        new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                                HttpResponseStatus.OK);
                response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html;charset=utf-8");
                response.content().writeBytes("sonic is a good man".getBytes());
                ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
            }
        }
    }

    public static void main(String[] args) {
        HttpServerTest server = new HttpServerTest();
        server.openServer(8080);
    }
}
