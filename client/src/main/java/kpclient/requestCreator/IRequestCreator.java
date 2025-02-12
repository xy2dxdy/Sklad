package kpclient.requestCreator;

import pojo.IPojo;
import request.IRequest;

import java.io.Serializable;

public interface IRequestCreator {
    IRequest CreateRequest(Serializable data);
}
